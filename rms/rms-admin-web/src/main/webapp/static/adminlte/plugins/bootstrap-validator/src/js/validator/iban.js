(function($) {
    $.fn.bootstrapValidator.validators.iban = {
        html5Attributes: {
            message: 'message',
            country: 'country'
        },

        /**
         * Validate an International Bank Account Number (IBAN)
         * To test it, take the sample IBAN from
         * http://www.nordea.com/Our+services/International+products+and+services/Cash+Management/IBAN+countries/908462.html
         *
         * @param {BootstrapValidator} validator The validator plugin instance
         * @param {jQuery} $field Field element
         * @param {Object} options Can consist of the following keys:
         * - message: The invalid message
         * - country: The ISO 3166-1 country code
         * @returns {Boolean}
         */
        validate: function(validator, $field, options) {
            var value = $field.val();
            if (value == '') {
                return true;
            }

            // See
            // http://www.swift.com/dsp/static/documents/IBAN_Registry.pdf
            // http://en.wikipedia.org/wiki/International_Bank_Account_Number#IBAN_formats_by_country
            var ibanRegex = {
                'AD': 'AD[0-9]{2}[0-9]{4}[0-9]{4}[A-Z0-9]{12}',                     // Andorra
                'AE': 'AE[0-9]{2}[0-9]{3}[0-9]{16}',                                // United Arab Emirates
                'AL': 'AL[0-9]{2}[0-9]{8}[A-Z0-9]{16}',                             // Albania
                'AO': 'AO[0-9]{2}[0-9]{21}',                                        // Angola
                'AT': 'AT[0-9]{2}[0-9]{5}[0-9]{11}',                                // Austria
                'AZ': 'AZ[0-9]{2}[A-Z]{4}[A-Z0-9]{20}',                             // Azerbaijan
                'BA': 'BA[0-9]{2}[0-9]{3}[0-9]{3}[0-9]{8}[0-9]{2}',                 // Bosnia and Herzegovina
                'BE': 'BE[0-9]{2}[0-9]{3}[0-9]{7}[0-9]{2}',                         // Belgium
                'BF': 'BF[0-9]{2}[0-9]{23}',                                        // Burkina Faso
                'BG': 'BG[0-9]{2}[A-Z]{4}[0-9]{4}[0-9]{2}[A-Z0-9]{8}',              // Bulgaria
                'BH': 'BH[0-9]{2}[A-Z]{4}[A-Z0-9]{14}',                             // Bahrain
                'BI': 'BI[0-9]{2}[0-9]{12}',                                        // Burundi
                'BJ': 'BJ[0-9]{2}[A-Z]{1}[0-9]{23}',                                // Benin
                'BR': 'BR[0-9]{2}[0-9]{8}[0-9]{5}[0-9]{10}[A-Z][A-Z0-9]',           // Brazil
                'CH': 'CH[0-9]{2}[0-9]{5}[A-Z0-9]{12}',                             // Switzerland
                'CI': 'CI[0-9]{2}[A-Z]{1}[0-9]{23}',                                // Ivory Coast
                'CM': 'CM[0-9]{2}[0-9]{23}',                                        // Cameroon
                'CR': 'CR[0-9]{2}[0-9]{3}[0-9]{14}',                                // Costa Rica
                'CV': 'CV[0-9]{2}[0-9]{21}',                                        // Cape Verde
                'CY': 'CY[0-9]{2}[0-9]{3}[0-9]{5}[A-Z0-9]{16}',                     // Cyprus
                'CZ': 'CZ[0-9]{2}[0-9]{20}',                                        // Czech Republic
                'DE': 'DE[0-9]{2}[0-9]{8}[0-9]{10}',                                // Germany
                'DK': 'DK[0-9]{2}[0-9]{14}',                                        // Denmark
                'DO': 'DO[0-9]{2}[A-Z0-9]{4}[0-9]{20}',                             // Dominican Republic
                'DZ': 'DZ[0-9]{2}[0-9]{20}',                                        // Algeria
                'EE': 'EE[0-9]{2}[0-9]{2}[0-9]{2}[0-9]{11}[0-9]{1}',                // Estonia
                'ES': 'ES[0-9]{2}[0-9]{4}[0-9]{4}[0-9]{1}[0-9]{1}[0-9]{10}',        // Spain
                'FI': 'FI[0-9]{2}[0-9]{6}[0-9]{7}[0-9]{1}',                         // Finland
                'FO': 'FO[0-9]{2}[0-9]{4}[0-9]{9}[0-9]{1}',                         // Faroe Islands
                'FR': 'FR[0-9]{2}[0-9]{5}[0-9]{5}[A-Z0-9]{11}[0-9]{2}',             // France
                'GB': 'GB[0-9]{2}[A-Z]{4}[0-9]{6}[0-9]{8}',                         // United Kingdom
                'GE': 'GE[0-9]{2}[A-Z]{2}[0-9]{16}',                                // Georgia
                'GI': 'GI[0-9]{2}[A-Z]{4}[A-Z0-9]{15}',                             // Gibraltar
                'GL': 'GL[0-9]{2}[0-9]{4}[0-9]{9}[0-9]{1}',                         // Greenland[
                'GR': 'GR[0-9]{2}[0-9]{3}[0-9]{4}[A-Z0-9]{16}',                     // Greece
                'GT': 'GT[0-9]{2}[A-Z0-9]{4}[A-Z0-9]{20}',                          // Guatemala
                'HR': 'HR[0-9]{2}[0-9]{7}[0-9]{10}',                                // Croatia
                'HU': 'HU[0-9]{2}[0-9]{3}[0-9]{4}[0-9]{1}[0-9]{15}[0-9]{1}',        // Hungary
                'IE': 'IE[0-9]{2}[A-Z]{4}[0-9]{6}[0-9]{8}',                         // Ireland
                'IL': 'IL[0-9]{2}[0-9]{3}[0-9]{3}[0-9]{13}',                        // Israel
                'IR': 'IR[0-9]{2}[0-9]{22}',                                        // Iran
                'IS': 'IS[0-9]{2}[0-9]{4}[0-9]{2}[0-9]{6}[0-9]{10}',                // Iceland
                'IT': 'IT[0-9]{2}[A-Z]{1}[0-9]{5}[0-9]{5}[A-Z0-9]{12}',             // Italy
                'JO': 'JO[0-9]{2}[A-Z]{4}[0-9]{4}[0]{8}[A-Z0-9]{10}',               // Jordan
                'KW': 'KW[0-9]{2}[A-Z]{4}[0-9]{22}',                                // Kuwait
                'KZ': 'KZ[0-9]{2}[0-9]{3}[A-Z0-9]{13}',                             // Kazakhstan
                'LB': 'LB[0-9]{2}[0-9]{4}[A-Z0-9]{20}',                             // Lebanon
                'LI': 'LI[0-9]{2}[0-9]{5}[A-Z0-9]{12}',                             // Liechtenstein
                'LT': 'LT[0-9]{2}[0-9]{5}[0-9]{11}',                                // Lithuania
                'LU': 'LU[0-9]{2}[0-9]{3}[A-Z0-9]{13}',                             // Luxembourg
                'LV': 'LV[0-9]{2}[A-Z]{4}[A-Z0-9]{13}',                             // Latvia
                'MC': 'MC[0-9]{2}[0-9]{5}[0-9]{5}[A-Z0-9]{11}[0-9]{2}',             // Monaco
                'MD': 'MD[0-9]{2}[A-Z0-9]{20}',                                     // Moldova
                'ME': 'ME[0-9]{2}[0-9]{3}[0-9]{13}[0-9]{2}',                        // Montenegro
                'MG': 'MG[0-9]{2}[0-9]{23}',                                        // Madagascar
                'MK': 'MK[0-9]{2}[0-9]{3}[A-Z0-9]{10}[0-9]{2}',                     // Macedonia
                'ML': 'ML[0-9]{2}[A-Z]{1}[0-9]{23}',                                // Mali
                'MR': 'MR13[0-9]{5}[0-9]{5}[0-9]{11}[0-9]{2}',                      // Mauritania
                'MT': 'MT[0-9]{2}[A-Z]{4}[0-9]{5}[A-Z0-9]{18}',                     // Malta
                'MU': 'MU[0-9]{2}[A-Z]{4}[0-9]{2}[0-9]{2}[0-9]{12}[0-9]{3}[A-Z]{3}',// Mauritius
                'MZ': 'MZ[0-9]{2}[0-9]{21}',                                        // Mozambique
                'NL': 'NL[0-9]{2}[A-Z]{4}[0-9]{10}',                                // Netherlands
                'NO': 'NO[0-9]{2}[0-9]{4}[0-9]{6}[0-9]{1}',                         // Norway
                'PK': 'PK[0-9]{2}[A-Z]{4}[A-Z0-9]{16}',                             // Pakistan
                'PL': 'PL[0-9]{2}[0-9]{8}[0-9]{16}',                                // Poland
                'PS': 'PS[0-9]{2}[A-Z]{4}[A-Z0-9]{21}',                             // Palestinian
                'PT': 'PT[0-9]{2}[0-9]{4}[0-9]{4}[0-9]{11}[0-9]{2}',                // Portugal
                'QA': 'QA[0-9]{2}[A-Z]{4}[A-Z0-9]{21}',                             // Qatar
                'RO': 'RO[0-9]{2}[A-Z]{4}[A-Z0-9]{16}',                             // Romania
                'RS': 'RS[0-9]{2}[0-9]{3}[0-9]{13}[0-9]{2}',                        // Serbia
                'SA': 'SA[0-9]{2}[0-9]{2}[A-Z0-9]{18}',                             // Saudi Arabia
                'SE': 'SE[0-9]{2}[0-9]{3}[0-9]{16}[0-9]{1}',                        // Sweden
                'SI': 'SI[0-9]{2}[0-9]{5}[0-9]{8}[0-9]{2}',                         // Slovenia
                'SK': 'SK[0-9]{2}[0-9]{4}[0-9]{6}[0-9]{10}',                        // Slovakia
                'SM': 'SM[0-9]{2}[A-Z]{1}[0-9]{5}[0-9]{5}[A-Z0-9]{12}',             // San Marino
                'SN': 'SN[0-9]{2}[A-Z]{1}[0-9]{23}',                                // Senegal
                'TN': 'TN59[0-9]{2}[0-9]{3}[0-9]{13}[0-9]{2}',                      // Tunisia
                'TR': 'TR[0-9]{2}[0-9]{5}[A-Z0-9]{1}[A-Z0-9]{16}',                  // Turkey
                'VG': 'VG[0-9]{2}[A-Z]{4}[0-9]{16}'                                 // Virgin Islands, British
            };
            value = value.replace(/[^a-zA-Z0-9]/g, '').toUpperCase();
            var country = options.country || value.substr(0, 2);
            if (!ibanRegex[country]) {
                return false;
            }
            if (!(new RegExp('^' + ibanRegex[country] + '$')).test(value)) {
                return false;
            }

            value = value.substr(4) + value.substr(0, 4);
            value = $.map(value.split(''), function(n) {
                var code = n.charCodeAt(0);
                return (code >= 'A'.charCodeAt(0) && code <= 'Z'.charCodeAt(0))
                        // Replace A, B, C, ..., Z with 10, 11, ..., 35
                        ? (code - 'A'.charCodeAt(0) + 10)
                        : n;
            });
            value = value.join('');

            var temp   = parseInt(value.substr(0, 1), 10),
                length = value.length;
            for (var i = 1; i < length; ++i) {
                temp = (temp * 10 + parseInt(value.substr(i, 1), 10)) % 97;
            }
            return (temp == 1);
        }
    };
}(window.jQuery));
