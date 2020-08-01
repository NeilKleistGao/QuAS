<template>
    <div>
        <quas-nav :labels="nav_labels" :links="nav_links"/>

        <h1 class="quas-fill-header">{{title}}——问卷分析</h1>
        <br/>

        <div class="quas-analysis-content">
            <div class="quas-analysis-cell">
                <quas-list title="分析列表" :items="analyze_items"/>
            </div>
            <div class="quas-analysis-cell">
                <!-- TODO:-->
            </div>
        </div>
    </div>
</template>

<script>
    import QuasNav from "@/components/QuasWebUI/QuasNav";
    import QuasList from "@/components/QuasWebUI/QuasList";
    export default {
        name: "AnalysisLayout",
        components: {QuasList, QuasNav},
        data() {
            return {
                nav_labels: ["问卷设计测试", "问卷填写测试", "问卷结果测试", "问卷分析测试"],
                nav_links: ["#/questionnaire/design/3154", "#/questionnaire/fill/3154", "#/questionnaire/result/3154", "#/questionnaire/analysis/3154"],
                title: "关于问卷测试的问卷调查",
                analyze_items: ["分析1", "分析2", "分析3"]
            }
        }
    }
</script>

<style scoped>

</style>