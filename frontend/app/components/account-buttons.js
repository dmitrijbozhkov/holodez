import Component from '@ember/component';
import { inject } from "@ember/service";
import service from 'ember-service/inject';

export default Component.extend({
    session: inject("session"),
    router: service("router"),
    classNames: ["field", "is-grouped"],
    actions: {
        logout() {
            this.get("session").invalidate();
            this.get("router").transitionTo("index");
        }
    }
});