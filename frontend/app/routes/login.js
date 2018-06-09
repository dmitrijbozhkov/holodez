import Route from '@ember/routing/route';
import UnauthenticatedRouteMixin from 'ember-simple-auth/mixins/unauthenticated-route-mixin';
import { inject } from "@ember/service";

export default Route.extend({
    session: inject('session'),
    actions: {
        submit(username, password) {
            const credentials = { username: username, password: password };
            this.get("session").authenticate("authenticator:token", credentials);
            this.transitionTo("index");
        }
    }
});
