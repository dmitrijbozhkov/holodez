import EmberRouter from '@ember/routing/router';
import config from './config/environment';

const Router = EmberRouter.extend({
  location: config.locationType,
  rootURL: config.rootURL
});

Router.map(function() {
  this.route('login');
  this.route('register');
  this.route('profile');
  this.route('first-aid', function() {});
  this.route('case-dashboard', function() {});
  this.route('knowledgebase', function() {});
  this.route('search', function() {});
});

export default Router;
