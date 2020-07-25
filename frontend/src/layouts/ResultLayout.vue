<template>
    <div>
        <quas-nav :labels="nav_labels" :links="nav_links"/>

        <h1 class="quas-fill-header">{{title}}——填写结果</h1>
        <br/>

        <quas-tab :labels="tab_labels">
            <template slot="问卷数据">
                test1
                <!--TODO-->
            </template>
            <template slot="统计图表">
                test2
            </template>
        </quas-tab>
    </div>
</template>

<script>
    import QuasNav from "@/components/QuasWebUI/QuasNav";
    import QuasTab from "@/components/QuasWebUI/QuasTab";
    export default {
        name: "ResultLayout",
        components: {QuasTab, QuasNav},
        data() {
            return {
                nav_labels: ["问卷设计测试", "问卷填写测试", "问卷结果测试"],
                nav_links: ["#/questionnaire/design/3154", "#/questionnaire/fill/3154", "#/questionnaire/result/3154"],
                title: "关于问卷测试的问卷调查",
                tab_labels: ["问卷数据", "统计图表"]
            };
        }
    }
</script>

<style scoped>

</style>