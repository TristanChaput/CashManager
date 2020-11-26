'use strict';

const { describe, it, expect } = require('@jest/globals');
const { calcTotalPrice } = require('../../../../api/cart/services/cart');

describe('cart integration test', () => {
  const products = [
    {
      id: 1,
      name: 'Coffret voyage aux Caraïbes',
      description: 'Les Caraïbes, (également nommées la Caraïbe, l\'espace caraïbe, ou encore l\'espace des Caraïbes) sont une région des Amériques qui comprend la mer des Caraïbes, ses îles (certaines entourées par la mer des Caraïbes et d\'autres bordant à la fois cette dernière et l\'océan Atlantique Nord) et les côtes environnantes. La région est située au sud-est du golfe du Mexique et du continent nord-américain, à l\'est de l\'Amérique centrale et au nord de l\'Amérique du Sud. ',
      slug: 'coffret-voyage-aux-caraibes',
      quantity: 96380,
      price: 163,
      published_at: 1605873906146,
      created_by: null,
      updated_by: 1,
      created_at: 1605873906339,
      updated_at: 1605875482140
    },
    {
      id: 2,
      name: 'Coffret road trip aux USA',
      description: 'La route serpente de San Francisco à Los Angeles sur environ 200 km, qui se savourent, de virage en virage – et peuvent faire regretter aux plus délicats d’être passager et non conducteur. Elle étonne aussi, quand la plus bucolique des routes se transforme peu à peu pour devenir une autoroute apocalyptique aux heures de pointe vers Los Angeles.',
      slug: 'coffret-road-trip-aux-usa',
      quantity: 50695,
      price: 862,
      published_at: 1605873906146,
      created_by: null,
      updated_by: 1,
      created_at: 1605873906382,
      updated_at: 1605876888261
    }
  ];

  it('should calc the price of all products in cart', async () => {
    const price = await calcTotalPrice(products);

    expect(price).toBe(1025);
  });
});
