'use strict';

/**
 * An asynchronous bootstrap function that runs before
 * your application gets started.
 *
 * This gives you an opportunity to set up your data model,
 * run jobs, or perform some special logic.
 *
 * See more details here: https://strapi.io/documentation/3.0.0-beta.x/concepts/configurations.html#bootstrap
 */

const fs = require('fs');
const accounts = require('../../data/accounts.json');
const products = require('../../data/products.json');
const users = require('../../data/users_permissions_user');

async function isFirstRun() {
  const pluginStore = strapi.store({
    environment: strapi.config.environment,
    type: 'type',
    name: 'setup',
  });
  const initHasRun = await pluginStore.get({ key: 'initHasRun' });
  await pluginStore.set({ key: 'initHasRun', value: true });
  return !initHasRun;
}

async function setPermissions(type, newPermissions) {
  // Find the ID of the public role

  try {
    const role = await strapi.query('role', 'users-permissions').findOne({ type });

    // List all available permissions
    const permissions = await strapi
      .query('permission', 'users-permissions')
      .find({ type: 'application', role: role.id });

    // Update permission to match new config
    const controllersToUpdate = Object.keys(newPermissions);
    const updatePromises = permissions
      .filter((permission) => {
        // Only update permissions included in newConfig
        if (!controllersToUpdate.includes(permission.controller)) {
          return false;
        }
        return newPermissions[permission.controller].includes(permission.action);
      })
      .map((permission) => {
        // Enable the selected permissions
        return strapi
          .query('permission', 'users-permissions')
          .update({ id: permission.id }, { enabled: true });
      });
    await Promise.all(updatePromises);
  } catch (e) {
    console.error('error: ' + e);
  }
}

function getFilesizeInBytes(filePath) {
  const stats = fs.statSync(filePath);
  return stats['size'];
}

function getMimeFromPath(filePath) {
  const execSync = require('child_process').execSync;
  const mimeType = execSync('file --mime-type -b "' + filePath + '"').toString();
  return mimeType.trim();
}

function getFileData(fileName) {
  const filePath = `./data/uploads/${fileName}`;

  // Parse the file metadata
  const size = getFilesizeInBytes(filePath);
  const mimeType = getMimeFromPath(filePath);

  return {
    path: filePath,
    name: fileName,
    size,
    type: mimeType,
  };
}

// Create an entry and attach files if there are any
async function createEntry(model, entry, files = null) {
  try {
    const createdEntry = await strapi.query(model).create(entry);

    if (files) {
      await strapi.entityService.uploadFiles(createdEntry, files, {
        model,
      });
    }
  } catch (e) {
    strapi.log.debug(e);
  }
}

async function importUsers() {
  const avatar = getFileData('avatar.png');
  const files = {
    avatar,
  };

  return users.map(async (user) => {
    await strapi.plugins['users-permissions'].services.user.add(user);

    await strapi.entityService.uploadFiles(user, files, {
      model: 'users-permissions_user',
    });
  });
}

async function importProducts() {
  return products.map(async (product) => {
    await createEntry('product', product, { image: getFileData(`${product.slug}.jpg`) });
  });
}

async function importAccounts() {
  return accounts.map(async (account) => {
    await createEntry('account', account);
  });
}

async function importSeedData() {
  // Create all entries
  await importUsers();
  await importAccounts();
  await importProducts();

  await setPermissions('authenticated', {
    account: ['count', 'find', 'findone', 'create', 'update', 'delete'],
    user: ['findone', 'create', 'update', 'me'],
  });
}

module.exports = async () => {
  const shouldImportSeedData = await isFirstRun();

  if (shouldImportSeedData) {
    try {
      console.log('Setting up your starter...');
      await importSeedData();
      console.log('Ready to go');
    } catch (error) {
      console.log('Could not import seed data');
      console.error(error);
    }
  }
};
