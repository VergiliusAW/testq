import React from "react";

export default class Profile extends React.Component {
    state = {
        isLoaded: false,
        body: "",
    };

    componentDidMount() {
        this.getData()
    }

    getData() {
        fetch("/api"+window.location.pathname)
            .then((response) => response.json())
            .then((responseJson) => {
                console.log(responseJson)
                this.setState({ body: responseJson.body });
                this.setState({ isLoaded: true });
            })
            .catch((error) => {
                this.setState({ isLoaded: false });
                console.error(error);
            });
    }

    render() {
        return (
            <div>
                {this.state.isLoaded && (
                    <div className={"profile"}>
                        <p>{this.state.body}</p>
                    </div>
                )}
            </div>
        );
    }
}
