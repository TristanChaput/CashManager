'use strict';

const { describe, it, expect } = require('@jest/globals');
const request = require('supertest');
const accounts = require('../../../data/accounts.json');

describe('account integration test', () => {
  it('should return the accounts of the user', async done => {
    request(strapi.server)
      .get('/accounts')
      .set('accept', 'application/json')
      .set('Content-Type', 'application/json')
      .expect('Content-Type', /json/)
      .expect(200)
      .then(data => {
        expect(data.body).toHaveLength(accounts.length());
        expect(data.body).toBe(accounts);
      });

    done();
  });

  it('should return the requested account', async done => {
    request(strapi.server)
      .get(`/accounts/${accounts[0].id}`)
      .set('accept', 'application/json')
      .set('Content-Type', 'application/json')
      .expect('Content-Type', /json/)
      .expect(200)
      .then(data => {
        expect(data.body).toBe(accounts[0]);
      });

    done();
  });
});
