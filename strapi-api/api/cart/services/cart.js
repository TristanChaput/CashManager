'use strict';

/**
 * Read the documentation (https://strapi.io/documentation/v3.x/concepts/services.html#core-services)
 * to customize this service
 */

module.exports = {
  async calcTotalPrice (products) {
    const { price } = products.reduce((acc, curr) => ({
      price: acc.price + curr.price
    }));

    return price;
  }
};
