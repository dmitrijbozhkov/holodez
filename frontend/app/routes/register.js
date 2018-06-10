import Route from '@ember/routing/route';

export default Route.extend({
    creatingNotificationOptions: {
        positionClass: "toast-top-center",
        preventDuplicates: true,
        timeOut: "50000"
    },
    responseNotificationOptions: {
        closeButton: true,
        positionClass: "toast-top-center",
        preventDuplicates: true,
        timeOut: "4000"
    },
    model() {
        return {
            form: this.get("store").createRecord("register-model", { role: "ROLE_DOCTOR", sex: "male" }),
            roles: [{ id: "ROLE_DOCTOR", name:"Доктором" }, { id: "ROLE_MEDICAL_WORKER", name: "Медицинским работником" }],
            sexes: [{ id: "male", name:"Мужской" }, { id: "female", name: "Женский" }]
        };
    },
    actions: {
        submit() {
            console.log(this.modelFor("register").form.birthday);
            let loadToast = this.toast.info("Пожалуйста подождите...", "Создание нового пользователя", this.get("creatingNotificationOptions"));
            this.modelFor("register").form.save().then((f, s, t) => {
                console.log(f, s, t);
                this.toast.remove(loadToast);
                this.toast.success("Аккаунт создан", "", this.get("responseNotificationOptions"));
                this.transitionTo("login");
            }).catch((f, s, t) => {
                console.log(f, s, t);
                this.toast.remove(loadToast);
                this.toast.error("Ошибка", "", this.get("responseNotificationOptions"));
            });
        }
    }
});
