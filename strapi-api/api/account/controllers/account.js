'use strict';

/**
 * Read the documentation (https://strapi.io/documentation/v3.x/concepts/controllers.html#core-controllers)
 * to customize this controller
 */

module.exports = {
  async pay(ctx) {
    const cart = await strapi.services.cart.findOne({ user: ctx.state.user.id });

    if (cart) {
      const account = await strapi.services.account.findOne({ user: ctx.state.user.id });

      if (account) {
        if (account.amount < cart.total) {
          return ctx.badRequest('You don\'t have enough money !');
        }

        await strapi.services.account.update(account.id, {
          amount: account.amount - cart.total,
        });
        await strapi.plugins['users-permissions'].services.user.edit(cart.user.id, {
          products: cart.products,
        });
        await strapi.services.cart.update(cart.id, {
          products: [],
          total: 0,
        });
      }
      return {
        message: 'Congratulation. Have good holidays !'
      }
    }

    return ctx.badRequest('You can\'t perform this action');
  }
};
