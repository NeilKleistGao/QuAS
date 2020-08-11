<template>
    <div>
        <div class="quas-hollow-circle"></div>
        <div class="quas-horizontal-line"></div>
        <label>{{label}}</label>
        <quas-dropdown-input style="display:inline-block;" :items="items_cut" :hidden_icon="true" v-model="result"/>
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
            },
            /**
             * 选择结果的下标，-1为最后一项（即结束）
             */
            value: {
                type: Number,
                required: true
            }
        },
        data() {
            return {
                result: this.items[(this.value + this.items.length) % this.items.length],
                items_cut: this.items
            };
        },
        watch: {
            result(new_value) {
                /**
                 * 更新选项内容
                 * @event{oninput}
                 */
                for (let i = 0; i < this.items.length; i++) {
                    if (new_value == this.items[i]) {
                        if (i === this.items.length - 1) {
                            this.$emit("input", -1);
                        }
                        else {
                            this.$emit("input", i);
                        }
                    }
                }
            }
        }
    }
</script>

<style scoped>
    label {
        display: inline-block;
        margin-left: 10px;
    }
</style>