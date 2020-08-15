<template>
    <div class="custom-control custom-radio">
        <div v-if="!editable">
            <input ref="box" class="custom-control-input" type="radio" :id="'radio' + label" :name="name" v-model="selected_text">
            <label class="custom-control-label" :for="'radio' + label"> {{label}} </label>
        </div>
        <div v-else>
            <quas-text-box v-model="label_text"/>
        </div>
    </div>
</template>

<script>
    import QuasTextBox from "@/components/QuasQuestionnaire/QuasTextBox";
    export default {
        name: "QuasRadio",
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
             * @private
             */
            updateSelect() {
                this.selected_text = this.label;
                /**
                 * 更新选中结果
                 * @event{oninput}
                 */

            }
        },
        watch: {
            label_text(new_value) {
                /**
                 * 更新标签文本
                 * @event{oninput}
                 */
                this.$emit("input", new_value);
            },
            selected_text() {
                console.log(this.label)
                this.$emit("input", this.label);
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
    label {
        margin-left: 0.3rem;
    }
</style>