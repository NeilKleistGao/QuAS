<template>
    <div>
        <textarea :class="state_class" v-model="content"/>
        <small v-if="state_class == 'quas-overflow-rich'" class="quas-warning-text">{{warning_text}}</small>
    </div>
</template>

<script>
    export default {
        name: "QuasRichText",
        props: {
            /**
             * 富文本框内的文本内容
             */
            value: {
                type: String
            },
            /**
             * 最大字数限制，-1为不设限制
             */
            max: {
                type: Number,
                default: -1
            },
            /**
             * 最少字数限制，-1为不设
             */
            min: {
                type: Number,
                default: -1
            }
        },
        data() {
            return {
                state_class: "quas-rich-text",
                warning_text: "",
                content: this.value
            };
        },
        watch: {
            content(new_value) {
                /**
                 * 更新富文本框内容
                 * @event{oninput}
                 */
                this.$emit("input", new_value);

                if ((this.min != -1 && new_value.length < this.min) ||
                    (this.max != -1 && new_value.length > this.max)) {
                    this.state_class = "quas-overflow-rich";

                    if (new_value.length < this.min) {
                        this.warning_text = "至少需要填写" + this.min.toString() + "个字";
                    }
                    else {
                        this.warning_text = "最多只能填写" + this.max.toString() + "个字";
                    }
                }
                else {
                    this.state_class = "quas-rich-text";
                    this.warning_text = "";
                }
            }
        }
    }
</script>

<style scoped>

</style>