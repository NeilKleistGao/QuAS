import Vue from "vue";
import VueRouter from "vue-router";

import App from "@/./App"
import DesignLayout from "@/layouts/DesignLayout";
import FillLayout from "@/layouts/FillLayout";

Vue.use(VueRouter);

const routes = [
    {
        path: "/questionnaire",
        component: App,
        children: [
            {
                path: "design/:id",
                component: DesignLayout
            },
            {
                path: "fill/:id",
                component: FillLayout
            }
        ]
    },
    {
        path: "/",
        redirect: "/questionnaire/fill/3154"
    }
];

let router = new VueRouter({
    routes
});

export default router;