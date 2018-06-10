"use strict";

const EmberApp = require("ember-cli/lib/broccoli/ember-app");
var fs = require('fs');

module.exports = function(defaults) {
    let app;
    let imgDir = "../src/main/resources/static/images";
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
                "vendor-css": { content: "th:href='@{/css/vendor.css}'" },
                "app-css": { content: "th:href='@{/css/holodez.css}'" },
                "vendor-js": { content: "th:src='@{/js/vendor.js}'" },
                "app-js": { content: "th:src='@{/js/holodez.js}'" },
                "thymeleaf": { content: " xmlns:th='http://www.thymeleaf.org'" }
            }
          });
        if (!fs.existsSync(imgDir)){
            fs.mkdirSync(imgDir);
        }
    } else { // ember build
        app = new EmberApp(defaults, {
            inlineContent: {
                "vendor-css": { content: "href='/assets/vendor.css'" },
                "app-css": { content: "href='/assets/frontend.css'" },
                "vendor-js": { content: "src='/assets/vendor.js'" },
                "app-js": { content: "src='/assets/frontend.js'" },
                "thymeleaf": { content: "" }
            }
        });
    }
    app.import("vendor/bulma.min.css");
  return app.toTree();
};
