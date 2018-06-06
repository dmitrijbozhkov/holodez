import { module, test } from 'qunit';
import { setupTest } from 'ember-qunit';

module('Unit | Route | case-dashboard/index', function(hooks) {
  setupTest(hooks);

  test('it exists', function(assert) {
    let route = this.owner.lookup('route:case-dashboard/index');
    assert.ok(route);
  });
});
