'use strict';

/**
 * Read the documentation (https://strapi.io/documentation/v3.x/concepts/services.html#core-services)
 * to customize this service
 */

module.exports = {
  async calcTotalPrice (products) {
    return products.reduce((sum, curr) => {
      if (!isNaN(curr.price)) {
        return sum + curr.price;
      }
      return sum + 0;
    }, 0);
  }
};
