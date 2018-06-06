import Component from '@ember/component';

export default Component.extend({
    tagName: "nav",
    classNames: ["navbar", "is-white", "topNav"],
    isBurgerOpen: false,
    actions : {
        toggleBurger() {
            this.toggleProperty("isBurgerOpen");
        }
    }
});
