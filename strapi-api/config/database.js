const parse = require('pg-connection-string').parse;
const config = parse(process.env.DATABASE_URL);

module.exports = () => ({
  defaultConnection: 'default',
  connections: {
    default: {
      connector: 'bookshelf',
      settings: {
        client: 'postgres',
        host: 'ec2-34-200-181-5.compute-1.amazonaws.com',
        port: 5432,
        database: 'd1cf1drgl2rbto',
        username: 'lpqfoapqznimjq',
        password: '914088cd6ff583e12033e8481b664859b1ba4d04f9a28c9de843e0b2754c66ba',
      },
      options: {
        ssl: true,
      },
      dialectOptions: {
        ssl: {
          require: true,
          rejectUnauthorized: true,
        },
        keepAlive: true,
      },
    },
  },
});
