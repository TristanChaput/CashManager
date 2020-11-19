module.exports = () => ({
  defaultConnection: 'default',
  connections: {
    default: {
      connector: 'bookshelf',
      settings: {
        client: 'sqlite',
        connection: './tmp/test.db',
      },
      options: {
        useNullAsDefault: true,
      },
    },
  },
});
