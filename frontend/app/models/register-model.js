import DS from 'ember-data';

export default DS.Model.extend({
    username: DS.attr("string"),
    password: DS.attr("string"),
    role: DS.attr("string"),
    name: DS.attr("string"),
    surname: DS.attr("string"),
    patronymic: DS.attr("string"),
    sex: DS.attr("string"),
    birthday: DS.attr("date"),
    image: DS.attr()
});
