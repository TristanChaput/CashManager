FROM strapi/base as build-back

RUN mkdir -p /srv/app
RUN chown $USER:$USER /srv/app
RUN chmod 755 /srv/app
WORKDIR /srv/app

COPY package.json yarn.lock /srv/app/

RUN yarn

COPY . /srv/app/

RUN NODE_ENV=development yarn build

EXPOSE 8080

CMD ["yarn", "start"]
