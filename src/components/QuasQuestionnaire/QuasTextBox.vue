<template>
    <div class="form-group has-danger">
        <label v-if="label != ''" class="form-control-label">{{label}}</label>
        <input :type="type" :class="state_class" required v-model="text">
        <div class="invalid-feedback">输入不符合要求</div>
    </div>
</template>

<script>
    export default {
        name: "QuasTextBox",
        props: {
            /**
             * 文本框内容，可选text, number, email
             */
            type: {
                type: String,
                default: "text"
            },
            /**
             * reg类型对应使用的正则表达式
             */
            reg: {
                type: RegExp
            },
            /**
             * 文本框内容
             */
            value: {
                type: String
            },
            label: {
                type: String,
                default: ""
            },
            nullable: {
                type: Boolean,
                default: false
            }
        },
        data() {
            return {
                text: this.value,
                state_class: "form-control"
            };
        },
        watch: {
            text(new_value) {
                this.check();
                /**
                 * 更新文本框内容
                 * @event{oninput}
                 */
                if (this.state_class === "form-control is-valid") {
                    this.$emit("input", new_value);
                }
            }
        },
        methods: {
            /**
             * 检查正则表达式合法性
             */
            check() {
                if (this.reg != null && this.reg != undefined) {
                    if (this.reg.test(this.text) && this.text !== "") {
                        this.state_class = "form-control is-valid";
                    }
                    else {
                        this.state_class = "form-control is-invalid";
                    }
                }
                else if (this.text != "" || this.nullable) {
                    this.state_class = "form-control is-valid";
                }
                else {
                    this.state_class = "form-control is-invalid";
                }
            }
        }
    }
</script>

<style scoped>

</style>