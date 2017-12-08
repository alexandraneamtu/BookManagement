/**
 * Created by alexandraneamtu on 05/12/2017.
 */

import React, {Component} from 'react';
import {
    Text,
    View,
    TouchableOpacity,
    TextInput,
    Linking,
    StyleSheet
} from 'react-native';
import {DatePickerDialog} from 'react-native-datepicker-dialog'
import moment from 'moment';


export class PrepareBook extends Component {
    static navigationOptions = {
        //title: 'Home',
        header:null,
    };

    constructor(props) {
        super(props);
        this.state = {
            dobText: moment(new Date()).format("DD-MMM-YY"),
            dobDate: null,
        };
    }

    /**
     * DOB textbox click listener
     */
    onDOBPress = () => {
        let dobDate = this.state.dobDate;

        if (!dobDate || dobDate === null) {
            dobDate = new Date();
            this.setState({
                dobDate: dobDate
            });
        }

        //To open the dialog
        this._textInput.open({
            date: dobDate,
            minDate: new Date() //To restrict future date
        });

    };

    /**
     * Call back for dob date picked event
     *
     */
    onDOBDatePicked = (date) => {
        this.setState({
            dobDate: date,
            dobText: moment(date).format("DD-MMM-YY")
        });
    };


    render() {
        return (
            <View>
                <View style={{alignItems: 'center'}}>
                    <Text style={{color: '#E91E63',
                        fontSize: 18,
                        padding: 26,}}>
                        -Prepare a book-
                    </Text>
                </View>
                <Text> Title: </Text>
                <TextInput
                    style={{height: 40, borderColor: 'gray'}}
                    onChangeText={(title) => this.setState({title})}
                />
                <Text style={{marginTop: 28}}> Name: </Text>
                <TextInput
                    style={{height: 40, borderColor: 'gray', marginBottom: 8}}
                    onChangeText={(name) => this.setState({name})}
                />
                <View>
                    <Text style={{marginTop: 28}}>Date: </Text>
                    <TouchableOpacity onPress={this.onDOBPress.bind(this)}>
                        <Text style={{height: 40, borderColor: 'gray', marginBottom: 8}}>{this.state.dobText}</Text>
                    </TouchableOpacity>
                </View>


                <TouchableOpacity style={styles.reserveButton}>
                    <Text style={styles.reserveButtonText}
                          onPress={() => {
                              receiver = "test@gmail.com";
                              subject = "Reserve a book";
                              body = "Hello,\nPlease keep " + this.state.title + " for "+ this.state.name +" until "+ this.state.dobText +".\n"+"Thank you!";
                              all = "mailto:" + receiver + "?subject=" + subject + "&body=" + body;
                              Linking.openURL(all)
                          }}> Reserve </Text>
                </TouchableOpacity>

                {/* Place the dialog component at end of your views and assign the references, event handlers to it.*/}
                <DatePickerDialog ref={dobDialog => this._textInput = dobDialog}
                                  onDatePicked={this.onDOBDatePicked.bind(this)}/>
            </View>
        );
    }
}

const styles = StyleSheet.create({
    reserveButton: {
        backgroundColor: '#E91E63',
        //borderRadius: 30,
        borderColor: '#ccc',
        alignItems: 'center',
        justifyContent: 'center',
        marginTop: 45
    },
    reserveButtonText: {
        color: '#fff',
        fontSize: 24,
    },
    container: {
        flex: 1, flexDirection: 'row'
    }
});

