'use strict';

/**
 * Read the documentation (https://strapi.io/documentation/v3.x/concepts/models.html#lifecycle-hooks)
 * to customize this model
 */

module.exports = {
  /**
   * Triggered before user creation.
   */
  lifecycles: {
    async beforeUpdate(params, data) {
      const cart = await strapi.query('cart').findOne({ id: data.id });

      const { price } = await strapi.services.cart.calcTotalPrice(cart.products);

      data.total = price;
    },
  },
};
