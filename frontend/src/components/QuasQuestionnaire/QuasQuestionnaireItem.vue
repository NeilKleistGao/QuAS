<template>
    <div>
        <quas-markdown-text v-model="information" :editable="editable" :markdown="information"/>

        <quas-check-box-group v-if="type == 'check'" :editable="editable" v-model="content"/>
        <quas-date-picker v-else-if="type == 'date' && !editable"/>
        <quas-dropdown v-else-if="type == 'drop'" :editable="editable" v-model="content"/>
        <quas-radio-group v-else-if="type == 'radio'" :editable="editable" v-model="content" :name="information"/>
        <quas-rich-text v-else-if="type == 'rich' && !editable"/>
        <quas-sort-list v-else-if="type == 'sort'" :editable="editable" v-model="content"/>
        <quas-text-box v-else-if="type == 'text' && !editable"/>
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
                content: this.value.content,
                information: this.value.information
            };
        },
        watch: {
            information(new_value) {
                this.$emit("input", {
                    information: new_value,
                    content: this.content
                });
            },
            content: {
                handler(new_value) {
                    this.$emit("input", {
                        information: this.information,
                        content: new_value
                    });
                },
                deep: true
            }
        }
    }
</script>

<style scoped>

</style>