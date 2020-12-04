'use strict';

const { describe, it, expect } = require('@jest/globals');
const { calcTotalPrice } = require('../../../../api/cart/services/cart');

describe('calc total price', () => {

  let products = [
    {
      price: 10,
    },
    {
      price: 11,
    }
  ]

  it('should return total price of the products', async () => {
    const total = await calcTotalPrice(products);

    expect(total).toBe(21);
  })

  it('should return total price of the products after updated', async () => {
    products.push({ price: 2 })

    const total = await calcTotalPrice(products);

    expect(total).toBe(23);
  })

  it('should return total price of an empty products array', async () => {
    const total = await calcTotalPrice([]);

    expect(total).toBe(0);
  })

  it('should return total price of an empty products array', async () => {
    products.push({ price: NaN })
    const total = await calcTotalPrice(products);

    expect(total).toBe(23);
  })

  it('should return total price of an empty products array', async () => {
    const total = await calcTotalPrice(products.filter(product => product.price !== 11));

    expect(total).toBe(12);
  })
});

