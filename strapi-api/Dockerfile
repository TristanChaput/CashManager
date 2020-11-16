FROM strapi/base as build-back

RUN MKDIR -p /srv/app
RUN sudo chown $USER:$USER /srv/app
RUN sudo chmod 755 /srv/app
WORKDIR /srv/app

COPY package*.json yarn.lock /srv/app/

RUN yarn install

COPY . /srv/app

RUN NODE_ENV=production yarn build

EXPOSE 8080

CMD ["yarn", "start"]
