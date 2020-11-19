import React from "react";
import "./Post.css";
import { Link } from "react-router-dom";

class Post extends React.Component {
    constructor(props) {
        super(props);
        this.state = {
            data: {
                id: 1,
                title: "TEMPLATE",
                body:
                    "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Mauris et metus nisl. Etiam nibh nibh, blandit ut ultrices vitae, dictum vitae nulla. Aenean viverra sapien at lacus feugiat, non mattis ex interdum. Sed sollicitudin erat vel mauris dapibus, quis suscipit sem maximus. Nam maximus libero ut orci aliquam, eget eleifend turpis elementum. Proin sit amet hendrerit urna, at consectetur nisi. Nullam sed sapien eget velit molestie gravida fringilla id velit. Aliquam varius diam urna, vitae tempus turpis ornare id. Fusce in sodales arcu. Pellentesque mattis urna ut dolor gravida commodo. Sed massa risus, maximus in libero eu, dapibus laoreet nisl. Cras mattis justo eget nisl bibendum, mattis accumsan ante posuere. Fusce nisi mauris, convallis pellentesque augue eu, cursus venenatis tortor.",
                author: "admin",
            },
            isLoaded: false,
        };
    }

    componentDidMount() {
        const url =
            window.location.protocol +
            "//" +
            window.location.hostname +
            (window.location.port ? ":" + window.location.port : "");
        fetch(url + "/api" + window.location.pathname)
            .then((response) => response.json())
            .then((responseJson) => {
                this.setState({ data: responseJson });
                this.setState({ isLoaded: true });
            })
            .catch((error) => {
                this.setState({ isLoaded: false });
                console.error(error);
            });
    }

    render() {
        return (
            <div className={"post"}>
                <Link to={"/"}>
                    <span>&#10094; Назад</span>
                </Link>
                {this.state.isLoaded && (
                    <>
                        <div className={"title"}>
                            <h3>{this.state.data.title}</h3>
                        </div>
                        <div className={"body"}>
                            {this.state.data.body.split("<br>").map((it, i) => (
                                <p key={"x" + i}>{it}</p>
                            ))}
                        </div>
                    </>
                )}
            </div>
        );
    }
}

export default Post;
