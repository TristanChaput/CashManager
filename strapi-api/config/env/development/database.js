module.exports = () => ({
  defaultConnection: 'default',
  connections: {
    default: {
      connector: 'bookshelf',
      settings: {
        client: 'sqlite',
        connection: './tmp/data.db',
      },
      options: {
        useNullAsDefault: true,
      },
    },
  },
});
