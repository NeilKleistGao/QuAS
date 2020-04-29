<template>
    <div>
        <label>{{label}}</label>
        <input :type="type" :class="state_class" required @change="check" v-model="text">
    </div>
</template>

<script>
    export default {
        name: "QuasTextBox",
        props: {
            type: {
                type: String,
                default: "text"
            },
            reg: {
                type: Object
            },
            value: {
                type: String
            },
            label: {
                type: String,
                require: true
            }
        },
        data() {
            return {
                text: this.value,
                state_class: "quas-text"
            };
        },
        watch: {
            test(new_value) {
                this.$emit("input", new_value);
            }
        },
        methods: {
            check() {
                if (this.type != "text" && this.reg != null && this.reg != undefined) {
                    if (this.reg.test(this.text) && this.text !== "") {
                        this.state_class = "quas-valid-text";
                    }
                    else {
                        this.state_class = "quas-invalid-text";
                    }
                }
                else if (this.text != "") {
                    this.state_class = "quas-valid-text";
                }
                else {
                    this.state_class = "quas-invalid-text";
                }
            }
        }
    }
</script>

<style scoped>

</style>