<template>
    <div>
        <div v-if="!editable">
            <input class="quas-check" ref="box" type="checkbox" :id="label" @click="updateSelect" :disabled="!selectable">
            <label :for="label"> {{label}} </label>
        </div>
        <div v-else>
            <input type="checkbox" class="quas-design-check" disabled>
            <input class="quas-design-text" type="text" v-model="label_text">
        </div>
    </div>
</template>

<script>
    export default {
        name: "QuasCheckBox",
        props: {
            /**
             * 是否可编辑
             */
            editable: {
                type: Boolean,
                default: false
            },
            /**
             * 多选框提示文本，仅在非可编辑下使用
             */
            label: {
                type: String
            },
            /**
             * 可编辑状态下为提示文本的值；非可编辑状态下为多选框状态列表，表示选中的内容
             */
            value: {
                required: true
            },
            /**
             * 是否可被选中，默认为可以，仅在非可编辑状态下使用
             */
            selectable: {
                type: Boolean,
                default: true
            }
        },
        data() {
            return {
                selected_list: this.value,
                label_text: this.value
            };
        },
        methods: {
            /**
             * 更新多选框选中状态
             * @private
             */
            updateSelect() {
                let index = this.selected_list.indexOf(this.text);
                if (index == -1) {
                    this.selected_list.push(this.text);
                }
                else {
                    this.selected_list.splice(index, 1);
                }

                this.$emit("input", this.selected_list);
            }
        },
        watch: {
            label_text(new_value) {
                /**
                 * 更新多选框提示标签文本
                 * @type {oninput}
                 */
                this.$emit("input", new_value);
            }
        },
        beforeMount() {
            if (!this.editable && this.text in this.selected_list) {
                this.$refs.box.checked = true;
            }
        }
    }
</script>

<style scoped>
    @import "../../style/form/questionnaire.css";

    label, input[type="text"] {
        font-size: 16px;
        position: relative;
        top: -6px;
    }
    label {
        margin-left: 0.3rem;
    }
</style>