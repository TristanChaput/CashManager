'use strict';

/**
 * Read the documentation (https://strapi.io/documentation/v3.x/concepts/controllers.html#core-controllers)
 * to customize this controller
 */

module.exports = {
  async download(ctx) {
    const file = await strapi.query('client-apk').find();

    if (file) {
      ctx.redirect(file[0].apk.url);
    }
  }
};
