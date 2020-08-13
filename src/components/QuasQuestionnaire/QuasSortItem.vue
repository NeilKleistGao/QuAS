<template>
    <div class="custom-control">
        <div v-if="!editable">
            <span class="badge badge-primary">{{label}}</span>
        </div>
        <div v-else>
            <quas-text-box :nullable="true" v-model="text"/>
        </div>
    </div>
</template>

<script>
    import QuasTextBox from "@/components/QuasQuestionnaire/QuasTextBox";
    export default {
        name: "QuasSortItem",
        components: {QuasTextBox},
        props: {
            /**
             * 是否可编辑
             */
            editable: {
                type: Boolean,
                default: false
            },
            /**
             * 排序选项提示文本，仅在非可编辑下使用
             */
            label: {
                type: String
            },
            /**
             * 编辑模式下提示文本
             */
            value: {
                type: String
            }
        },
        data() {
            return {
                text: this.value
            };
        },
        watch: {
            text(new_value) {
                /**
                 * 更新文本内容
                 * @event{oninput}
                 */
                this.$emit("input", new_value);
            }
        }
    }
</script>

<style scoped>
    label, input[type="text"] {
        font-size: 16px;
    }
    label {
        margin-left: 0.3rem;
    }
</style>