import DS from 'ember-data';

export default DS.Serializer.extend({
    serialize(snapshot, options) {
        this._super(...arguments);
        let data = {
            username:snapshot.record.username,
            password: snapshot.record.password,
            role: snapshot.record.role,
            name: snapshot.record.name,
            surname: snapshot.record.surname,
            patronymic: snapshot.record.patronymic,
            sex: snapshot.record.sex,
            birthday: snapshot.record.birthday
        };
        return JSON.stringify(data);
    },
    normalizeResponse(store, primaryModelClass, payload, id, requestType) {
        if ("error" in payload) {
            return payload;
        }
        return { data: payload };
    }
});