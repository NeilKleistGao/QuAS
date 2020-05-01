<template>
    <div>
        <label>{{label}}</label>
        <div :class="state_class" contenteditable="true" @input="updateText" ref="box"/>
        <small v-if="state_class == 'quas-overflow-rich'" class="quas-warning-text">{{warning_text}}</small>
    </div>
</template>

<script>
    export default {
        name: "QuasRichText",
        props: {
            label: {
                type: String,
                require: true
            },
            value: {
                type: String
            },
            max: {
                type: Number,
                default: -1
            },
            min: {
                type: Number,
                default: -1
            }
        },
        data() {
            return {
                state_class: "quas-rich-text",
                warning_text: ""
            };
        },
        methods: {
            updateText() {
                let text = this.$refs.box.innerHTML;
                this.$emit("input", text);

                if ((this.min != -1 && text.length < this.min) ||
                    (this.max != -1 && text.length > this.max)) {
                    this.state_class = "quas-overflow-rich";

                    if (text.length < this.min) {
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