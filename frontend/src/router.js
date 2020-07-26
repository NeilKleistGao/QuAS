import Vue from "vue";
import VueRouter from "vue-router";

import App from "@/./App"
import DesignLayout from "@/layouts/DesignLayout";
import FillLayout from "@/layouts/FillLayout";
import MobileFillLayout from "@/layouts/MobileFillLayout";
import ResultLayout from "@/layouts/ResultLayout";

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
            },
            {
                path: "mobile_fill/:id",
                component: MobileFillLayout
            },
            {
                path: "result/:id",
                component: ResultLayout
            }
        ]
    },
    {
        path: "/",
        redirect: "/questionnaire/result/3154"
    }
];

let router = new VueRouter({
    routes
});

export default router;