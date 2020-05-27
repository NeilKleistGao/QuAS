<template>
    <div>
        <quas-markdown-text/>
        <hr/>
        <quas-check-box-group v-if="type == 'check'"/>
        <quas-date-picker v-else-if="type == 'date'"/>
        <quas-dropdown v-else-if="type == 'drop'"/>
        <quas-radio-group v-else-if="type == 'radio'"/>
        <quas-rich-text v-else-if="type == 'rich'"/>
        <quas-sort-list v-else-if="type == 'sort'"/>
        <quas-text-box v-else-if="type == 'text'"/>
    </div>
</template>

<script>
    import QuasMarkdownText from "@/components/QuasQuestionnaire/QuasMarkdownText";
    import QuasCheckBoxGroup from "@/components/QuasQuestionnaire/QuasCheckBoxGroup";
    import QuasDatePicker from "@/components/QuasQuestionnaire/QuasDatePicker";
    import QuasDropdown from "@/components/QuasQuestionnaire/QuasDropdown";
    import QuasRadioGroup from "@/components/QuasQuestionnaire/QuasRadioGroup";
    import QuasRichText from "@/components/QuasQuestionnaire/QuasRichText";
    import QuasSortList from "@/components/QuasQuestionnaire/QuasSortList";
    import QuasTextBox from "@/components/QuasQuestionnaire/QuasTextBox";
    export default {
        name: "QuasQuestionnaireItem",
        components: {
            QuasTextBox,
            QuasSortList,
            QuasRichText, QuasRadioGroup, QuasDropdown, QuasDatePicker, QuasCheckBoxGroup, QuasMarkdownText},
        props: {
            type: {
                type: String,
                default: "none"
            },
            editable: {
                type: Boolean,
                default: false
            },
            value: {
                type: Object,
                required: true
            }
        },
        data() {
            return {

            };
        }
    }
</script>

<style scoped>

</style>