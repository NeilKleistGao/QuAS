<template>
    <div>
        <div v-if="!editable">
            <article v-html="compileMarkdown"></article>
        </div>
        <div v-else>
            <i class="iconfont icon-charutupian" @click="insertMultiMedia('pic')"/>
            <i class="iconfont icon-shengyinkai" @click="insertMultiMedia('voc')"/>
            <i class="iconfont icon-shexiangji" @click="insertMultiMedia('med')"/>

            <quas-setting-modal ref="modal" title="选择资源">
                <template slot="content">
                    <label>输入{{hint_text}}路径：</label>
                    <br/>
                    <input type="text" v-model="path" class="quas-design-text">
                    <button class="quas-operation-button" @click="upload">确定</button>
                </template>
            </quas-setting-modal>

            <textarea class="quas-rich-text" v-model="content" ref="box"/>
        </div>
    </div>
</template>

<script>
    import marked from "marked";
    import QuasSettingModal from "@/components/QuasQuestionnaire/QuasSettingModal";

    export default {
        name: "QuasMarkdownText",
        components: {QuasSettingModal},
        props: {
            editable: {
                type: Boolean,
                default: false
            },
            markdown: {
                type: String
            },
            value: {
                type: String
            }
        },
        data() {
            return {
                modal_type: "pic",
                path: "资源路径...",
                hint_text: "",
                content: this.value
            };
        },
        computed: {
            compileMarkdown() {
                return marked(this.markdown, { sanitize: true });
            }
        },
        methods: {
            insertMultiMedia(type) {
                this.modal_type = type;
                this.path = "资源路径...";

                if (type === "pic") {
                    this.hint_text = "图片";
                }
                else if (type === "voc") {
                    this.hint_text = "声音";
                }
                else {
                    this.hint_text = "视频";
                }

                this.$refs.modal.show();
            },
            upload() {
                this.$refs.modal.close();

                let item = "";
                if (this.modal_type === "pic") {
                    item = "![image](" + this.path + ")";
                }
                else if (this.modal_type === "voc") {
                    item = "<audio id='audio' controls='' preload='none'><source src='" + this.path + "'>";
                }
                else {
                    item = "<iframe width='400' height='300' src='" + this.path + "'>";
                }

                const area = this.$refs.box;
                if (area.selectionStart || area.selectionStart === 0) {
                    let start = area.selectionStart;
                    this.content = this.content.substr(0, start) + item + this.content.substr(start);
                }
                else {
                    this.content += item;
                }
            }
        },
        watch: {
            content(new_value) {
                this.$emit("input", new_value);
            }
        }
    }
</script>

<style scoped>
    i {
        color: gray;
        opacity: 50%;
        width: 64px;
        height: 64px;
        margin-left: 0.3em;
        margin-right: 0.3rem;
        transition: all 0.3s;
    }

    i:hover {
        color: var(--primary);
        opacity: 100%;
    }

    div .quas-rich-text {
        height: 80px;
    }

    label {
        margin-left: 0.3rem;
    }
</style>