<template>
    <div>
        <div v-if="!editable" :key="update_key" class="quas-dropdown-grid">
            <div v-for="(enable, index) in labels_enable" :key="index">
                <div v-if="enable">
                    <label>{{contents.labels[index]}}</label>
                    <quas-dropdown-input :placeholder="placeholder" v-model="result[index]" :items="getOptions(index)" :index="index" @click.native="modify(index)"/>
                </div>
            </div>
        </div>
        <div v-else>
            <quas-dropdown-tree v-model="items"/>
        </div>
    </div>
</template>

<script>
    import QuasDropdownTree from "@/components/QuasDropdownTree";
    import QuasDropdownInput from "@/components/QuasDropdownInput";
    export default {
        name: "QuasDropdown",
        components: {QuasDropdownInput, QuasDropdownTree},
        props: {
            editable: {
                type: Boolean,
                default: false
            },
            value: {
                type: Object,
                required: true
            },
            placeholder: {
                type: String,
                default: "..."
            },
            contents: {
                type: Object,
                required: true
            }
        },
        data() {
            return {
                result: this.value.result,
                labels: this.value.labels,
                items: this.value.items,
                update_key: 0,
                labels_enable: [true, false, false, false, false, false],
                previous_result: []
            };
        },
        methods: {
            getOptions(index) {
                let res = [];
                let len = this.contents.items.length;

                if (index === 0) {
                    for (let i = 0; i < len; i++) {
                        if (this.contents.items[i].level === 0) {
                            res.push(this.contents.items[i].label);
                        }
                    }
                }
                else {
                    for (let i = 0; i < len; i++) {
                        if (this.contents.items[i].label === this.result[index - 1] && i != len - 1) {
                            if (this.contents.items[i + 1].level > this.contents.items[i].level) {
                                res.push(this.contents.items[i + 1].label);
                                for (let j = i + 2; j < len; j++) {
                                    if (this.contents.items[j].level != this.contents.items[i + 1].level) {
                                        break;
                                    }

                                    res.push(this.contents.items[j].label);
                                }
                            }

                            break;
                        }
                    }
                }

                return res;
            },
            modify(index) {
                if (this.result[index] != this.previous_result[index]) {
                    this.previous_result[index] = this.result[index];
                    for (let j = index + 1; j < this.result.length; j++) {
                        this.labels_enable[j] = false;
                        this.previous_result[j] = this.result[j] = "";
                    }

                    let item_len = this.contents.items.length;
                    for (let j = 0; j < item_len; j++) {
                        if (this.contents.items[j].label === this.result[index] && j != item_len - 1) {
                            if (this.contents.items[j + 1].level > this.contents.items[j].level) {
                                this.labels_enable[this.contents.items[j + 1].level] = true;
                            }
                            break;
                        }
                    }

                    this.$emit("input", {
                        result: this.result
                    });

                    this.update_key++;
                }
            }
        },
        watch: {
            items: {
                handler(new_value) {
                    this.$emit("input", {
                        labels: this.labels,
                        items: new_value
                    });
                },
                deep: true
            }
        },
        beforeMount() {
            for (let item of this.result) {
                this.previous_result.push(item);
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
        margin-right: 0.5rem;
    }
</style>