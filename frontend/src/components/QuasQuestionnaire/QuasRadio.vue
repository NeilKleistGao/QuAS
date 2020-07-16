<template>
    <div>
        <div v-if="!editable">
            <input ref="box" class="quas-radio" type="radio" :id="label" :name="name" @click="updateSelect">
            <label :for="label"> {{label}} </label>
        </div>
        <div v-else>
            <input class="quas-design-radio" type="radio" disabled>
            <input class="quas-design-text" type="text" v-model="label_text">
        </div>
    </div>
</template>

<script>
    export default {
        name: "QuasRadio",
        props: {
            /**
             * 是否可编辑
             */
            editable: {
                type: Boolean,
                default: false
            },
            /**
             * 单选框提示文本，仅在非可编辑下使用
             */
            label: {
                type: String
            },
            /**
             * 可编辑状态下为提示文本的值；非可编辑状态下为单选框选中结果
             */
            value: {
                type: String,
                required: true
            },
            /**
             * 用于标识单选框组的文本
             */
            name: {
                type: String,
                required: true
            }
        },
        data() {
            return {
                label_text: this.value,
                selected_text: this.value
            };
        },
        methods: {
            /**
             * 更新选择状态
             */
            updateSelect() {
                this.selected_text = this.text;
                /**
                 * 更新选中结果
                 * @event{oninput}
                 */
                this.$emit("input", this.selected_text);
            }
        },
        watch: {
            label_text(new_value) {
                /**
                 * 更新标签文本
                 * @event{oninput}
                 */
                this.$emit("input", new_value);
            }
        },
        mounted() {
            this.$nextTick(() => {
                if (this.selected_text === this.label) {
                    this.$refs.box.checked = true;
                }
            });
        }
    }
</script>

<style scoped>
    @import "../../style/form/questionnaire.css";

    label, input[type="text"] {
        font-size: 16px;
    }
    label {
        margin-left: 0.3rem;
    }
</style>