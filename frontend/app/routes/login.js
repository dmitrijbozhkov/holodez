import Route from '@ember/routing/route';
import UnauthenticatedRouteMixin from 'ember-simple-auth/mixins/unauthenticated-route-mixin';
import { inject } from "@ember/service";

export default Route.extend({
    session: inject('session'),
    actions: {
        submit(username, password) {
            const credentials = { username: username, password: password };
            const authenticator = 'authenticator:token'; // or 'authenticator:jwt'
            console.log(this.get("session").get("isAuthenticated"))
            this.get('session').authenticate(authenticator, credentials);
        }
    }
});
