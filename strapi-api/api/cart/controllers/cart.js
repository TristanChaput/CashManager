'use strict';

/**
 * Read the documentation (https://strapi.io/documentation/v3.x/concepts/controllers.html#core-controllers)
 * to customize this controller
 */

const { sanitizeEntity } = require('strapi-utils');

module.exports = {
  /**
   * get user cart.
   */

  async find(ctx) {
    const entities = await strapi.services.cart.find({...ctx.query, user: ctx.state.user.id });

    return entities.map(entity => sanitizeEntity(entity, { model: strapi.models.cart }));
  },
};
