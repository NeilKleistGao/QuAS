<template>
    <div>
        <div v-if="!editable">
            <article v-html="compileMarkdown"></article>
        </div>
        <div v-else>
            <i class="iconfont icon-charutupian" @click="insertMultiMedia('pic')"/>
            <i class="iconfont icon-shengyinkai" @click="insertMultiMedia('voc')"/>
            <i class="iconfont icon-shexiangji" @click="insertMultiMedia('med')"/>

            <textarea style="min-width: 20rem;" class="form-control" rows="3" v-model="content" ref="box"/>
        </div>
    </div>
</template>

<script>
    import marked from "marked";

    export default {
        name: "QuasMarkdownText",
        props: {
            /**
             * 是否可编辑
             */
            editable: {
                type: Boolean,
                default: false
            },
            /**
             * 非编辑模式下用于渲染的markdown文本
             */
            markdown: {
                type: String
            },
            /**
             * 编辑模式下的文本内容
             */
            value: {
                type: String
            },
            /**
             * 是否为选填项，默认为否
             */
            nullable: {
                type: Boolean,
                default: false
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
                if (this.nullbale) {
                    return marked(this.markdown, { sanitize: true });
                }
                else {
                    return marked(this.markdown + "*", { sanitize: true });
                }

            }
        },
        methods: {
            /**
             * 打开插入多媒体modal
             * @param type 多媒体类型，包括pic(图片)，voc(声音)和视频
             * @private
             */
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
            /**
             * 将多媒体内容插入至markdown中
             * @private
             */
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
                /**
                 * 更新markdown文本的修改
                 * @event{oninput}
                 */
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
        color: #050709;
        opacity: 100%;
    }
</style>