const {describe, it, beforeAll, afterEach, afterAll} = require("@jest/globals");
const request = require('supertest');
const User = require('../../src/db/models/userModel');
const dbHandler = require("../db-handler");
const app = require("../../src/app");
const Account = require("../../src/db/models/accountModel");

/**
 * Connect to a new in-memory database before running any tests.
 */
beforeAll(async () => await dbHandler.connect());

/**
 * Clear all test data after every test.
 */
afterEach(async () => await dbHandler.clearDatabase());

/**
 * Remove and close the db and server.
 */
afterAll(async () => await dbHandler.closeDatabase());

describe('post api', () => {
    it('should return the user', async (done) => {
        Promise.all([
            User.create({ name: 'Jason' }),
        ]).then(([user]) => {
            const accounts = [
                {
                    number: 230071,
                    amount: 0.0,
                    user
                },
                {
                    number: 873330,
                    amount: 0.0,
                    user
                },
                {
                    number: 664552,
                    amount: 0.0,
                    user
                }
            ];
            Account.create(accounts);
        })

        const response = await request(app).get('/users/jason');

        expect(response.status).toBe(200);
        expect(response.body.user).toMatchObject(u);
        done();
    })
})