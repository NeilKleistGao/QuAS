<template>
    <div>
        <div v-if="!editable">
            <input class="quas-check" ref="box" type="checkbox" :id="text" @click="updateSelect" :disabled="!selectable">
            <label :for="text"> {{text}} </label>
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
            editable: {
                type: Boolean,
                default: false
            },
            text: {
                type: String
            },
            value: {
                require: true
            },
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
    @import "../style/form/form.css";

    label, input[type="text"] {
        font-size: 16px;
        position: relative;
        top: -6px;
    }
    label {
        margin-left: 0.3rem;
    }
</style>