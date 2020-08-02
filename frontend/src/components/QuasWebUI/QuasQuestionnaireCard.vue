<template>
    <div class="quas-questionnaire-card">
        <img src="/questionnaire.png">
        <label>{{title}}</label>
    </div>
</template>

<script>
    export default {
        name: "QuasQuestionnaireCard",
        props: {
            title: {
                type: String,
                required: true
            }
        },
        methods: {

        }
    }
</script>

<style scoped>

</style>