import DS from 'ember-data';
import { run } from '@ember/runloop';
import RSVP from 'rsvp';
import $ from 'jquery';

export default DS.Adapter.extend({
    createRecord(store, type, snapshot) {
        let data = this.serialize(snapshot, { includeId: true });
        return $.ajax({
          type: 'POST',
          url: `/api/auth/signup`,
          dataType: 'json',
          contentType: 'application/json',
          data: data
        });
        // new RSVP.Promise(function(resolve, reject) {
        //     ).then(function(data) {
        //       run(null, resolve, data);
        //     }, function(jqXHR) {
        //       jqXHR.then = null; // tame jQuery's ill mannered promises
        //       run(null, reject, jqXHR);
        //     });
        //   });
    }
});
