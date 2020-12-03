'use strict';

/**
 * Read the documentation (https://strapi.io/documentation/v3.x/concepts/controllers.html#core-controllers)
 * to customize this controller
 */

const { sanitizeEntity } = require('strapi-utils');

module.exports = {
  /**
   * Add product to user cart
   */
  async add(ctx) {
    const entity = await strapi.services.cart.findOne({ user: ctx.state.user.id });

    if (entity) {

      return strapi.services.cart.update(entity.id, {
        products: [...entity.products.map(product => product.id), ctx.params.id]
      })
    }
    ctx.badRequest('You can\'t perform this action');
  },

};
