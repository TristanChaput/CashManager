const {describe, it} = require("@jest/globals");
const request = require('supertest');
const app = require("../../src/app");

describe('post api', () => {
    it('should return the user', async (done) => {
        const response = await request(app).get('/users/jason');

        expect(response.status).toBe(200);
        expect(response.body.user).toMatchObject({
            name: 'Jason',
            accounts: [
                {
                    number: 230071,
                    amount: 0.0
                },
                {
                    number: 873330,
                    amount: 0.0
                },
                {
                    number: 664552,
                    amount: 0.0
                }
            ]
        });
        done();
    })
})