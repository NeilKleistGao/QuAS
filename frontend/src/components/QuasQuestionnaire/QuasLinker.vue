<template>
    <div>
        <div class="quas-hollow-circle"></div>
        <div class="quas-horizontal-line"></div>
        <label>{{label}}</label>
        <quas-dropdown-input style="display:inline-block;" :items="items" :hidden_icon="true"/>
    </div>
</template>

<script>
    import QuasDropdownInput from "@/components/QuasQuestionnaire/QuasDropdownInput";
    export default {
        name: "QuasLinker",
        components: {QuasDropdownInput},
        props: {
            /**
             * 显示的提示文本
             */
            label: {
                type: String,
                required: true
            },
            /**
             * 待选选项
             */
            items: {
                type: Array,
                required: true
            }
        }
    }
</script>

<style scoped>
    label {
        display: inline-block;
        margin-left: 5px;
    }
</style>