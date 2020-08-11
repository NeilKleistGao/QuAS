<template>
    <div>
        <quas-nav/>

        <br/>
        <div class="container-fluid">
            <div class="jumbotron">
                <h2>我的问卷</h2>
                <hr class="my-4"/>

                <div v-for="(sub_list, index) in list" :key="index">
                    <div v-for="item in sub_list" :key="item.title">
                        <quas-questionnaire-card :title="item.title" :qid="item.qid" v-model="item.state"/>
                    </div>
                </div>
            </div>
        </div>
    </div>
</template>

<script>
    import QuasNav from "@/components/QuasWebUI/QuasNav";
    import QuasQuestionnaireCard from "@/components/QuasWebUI/QuasQuestionnaireCard";
    // import QuasQuestionnaireCard from "@/components/QuasWebUI/QuasQuestionnaireCard";
    export default {
        name: "CollectionLayout",
        components: {QuasQuestionnaireCard, QuasNav},
        data() {
            return {
                list: [[{
                    title: "关于问卷测试的问卷调查",
                    state: "idle",
                    qid: "3154"
                }]]
            };
        }
    }
</script>

<style scoped>

</style>