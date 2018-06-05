"use strict";

const EmberApp = require("ember-cli/lib/broccoli/ember-app");

module.exports = function(defaults) {
    let app;
    if (process.env.EMBER_ENV === "production") { // ember build --output-path="../src/main/resources" --environment="production"
        app = new EmberApp(defaults, {
            outputPaths: {
              app: {
                html: "/templates/index.html",
                css: {
                  "app": "/static/css/holodez.css"
                },
                js: "/static/js/holodez.js"
              },
              vendor: {
                css: "/static/css/vendor.css",
                js: "/static/js/vendor.js"
              }
            },
            fingerprint: {
                enabled: false
            },
            inlineContent: {
                "vendor-css": { content: "th:href='@{assets/vendor.css}'" },
                "app-css": { content: "th:href='@{assets/holodez.css}'" },
                "vendor-js": { content: "th:src='@{assets/vendor.js}'" },
                "app-js": { content: "th:src='@{assets/holodez.js}'" },
                "thymeleaf": { content: " xmlns:th='http://www.thymeleaf.org'" }
            }
          });
    } else { // ember build
        app = new EmberApp(defaults, {
            inlineContent: {
                "vendor-css": { content: "href='assets/vendor.css'" },
                "app-css": { content: "href='assets/holodez.css'" },
                "vendor-js": { content: "src='assets/vendor.js'" },
                "app-js": { content: "src='assets/holodez.js'" },
                "thymeleaf": { content: "" }
            }
        });
    }
  return app.toTree();
};
